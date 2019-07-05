package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.sys.ao.SysI18nAO;
import com.cms.scaffold.sys.sys.bo.SysI18nBO;
import com.cms.scaffold.sys.sys.service.MyMessageSourceService;
import com.cms.scaffold.sys.sys.service.SysI18nService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhangjiaheng
 * @Description
 **/
@Service("messageSource")
public class MyMessageSource extends AbstractMessageSource implements ResourceLoaderAware, MyMessageSourceService {

    ResourceLoader resourceLoader;

    private static final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>(256);

    @Autowired
    SysI18nService sysI18nService;

    private final Logger logger = LoggerFactory.getLogger(MyMessageSource.class);

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        this.reload();
    }

    /**
     * 重新将数据库中的国际化配置加载
     */
    public void reload() {
        LOCAL_CACHE.clear();
        LOCAL_CACHE.putAll(loadAllMessageResourcesFromDB());
    }

    /**
     * 从数据库中获取所有国际化配置
     */
    public Map<String, Map<String, String>> loadAllMessageResourcesFromDB() {
        List<SysI18nBO> list = sysI18nService.findList(new SysI18nAO());
        if (CollectionUtils.isNotEmpty(list)) {
            final Map<String, String> zhCnMessageResources = new HashMap<>(list.size());
            final Map<String, String> enUsMessageResources = new HashMap<>(list.size());
            final Map<String, String> idIdMessageResources = new HashMap<>(list.size());
            for (SysI18nBO bo : list) {
                String name = bo.getModel() + "." + bo.getName();
                String zhText = bo.getZhCn();
                String enText = bo.getEnUs();
                String idText = bo.getInId();
                zhCnMessageResources.put(name, zhText);
                enUsMessageResources.put(name, enText);
                idIdMessageResources.put(name, idText);
            }
            LOCAL_CACHE.put("zh", zhCnMessageResources);
            LOCAL_CACHE.put("en", enUsMessageResources);
            LOCAL_CACHE.put("in", idIdMessageResources);
        }
        return MapUtils.EMPTY_MAP;
    }

    /**
     * 从缓存中取出国际化配置对应的数据 或者从父级获取
     *
     * @param code
     * @param locale
     * @return
     */
    public String checkFromCachedOrBundResource(String code, Locale locale) {
        String language = locale.getLanguage();
        Map<String, String> props = LOCAL_CACHE.get(language);
        if (null != props && props.containsKey(code)) {
            return props.get(code);
        } else {
            try {
                if (null != this.getParentMessageSource()) {
                    return this.getParentMessageSource().getMessage(code, null, locale);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return code;
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader == null ? new DefaultResourceLoader() : resourceLoader);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = checkFromCachedOrBundResource(code, locale);
        MessageFormat messageFormat = new MessageFormat(msg, locale);
        return messageFormat;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return checkFromCachedOrBundResource(code, locale);
    }
}

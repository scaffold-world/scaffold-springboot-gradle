<#if messages?? && messages?size gt 0>
<#list messages as item>
${item.name} = ${item.value}
</#list>
</#if>

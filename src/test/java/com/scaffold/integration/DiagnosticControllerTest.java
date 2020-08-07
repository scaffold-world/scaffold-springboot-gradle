package com.scaffold.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class DiagnosticControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturn200ForDiagnostic() throws Exception {
        this.mvc.perform(get("/diagnostic/")).andExpect(status().isOk())
            .andExpect(jsonPath("$.endpoints[0]").value("/diagnostic/status/heartbeat"))
            .andExpect(jsonPath("$.endpoints[1]").value("/diagnostic/status/diagnosis"))
            .andExpect(jsonPath("$.endpoints[2]").value("/diagnostic/status/nagios"))
            .andExpect(jsonPath("$.endpoints[3]").value("/diagnostic/version"));
    }

    @Test
    public void shouldReturn200ForHeartbeat() throws Exception {
        this.mvc.perform(get("/diagnostic/status/heartbeat")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturn200ForDiagnosis() throws Exception {
        this.mvc.perform(get("/diagnostic/status/diagnosis"))
            .andExpect(status().isOk())
            .andExpect(content().string("OK"));
    }

    @Test
    public void shouldReturn200ForNagiosIfDatabaseIsHealthy() throws Exception {
        this.mvc.perform(get("/diagnostic/status/nagios"))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAppVersion() throws Exception {
        this.mvc.perform(get("/diagnostic/version"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
}

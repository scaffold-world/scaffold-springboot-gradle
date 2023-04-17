package com.scaffold.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.scaffold.api.diagnosis.DatabaseHealthCheck;
import com.scaffold.api.diagnosis.DiagnosticController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class DiagnosticControllerTest {

    @Mock
    private DatabaseHealthCheck healthCheck;

    @InjectMocks
    private DiagnosticController controller;

    @Nested
    class Nagios {

        @Test
        void shouldReturnOKIfDatabaseHealthCheckPass() {
            when(healthCheck.isHealthy()).thenReturn(true);
            var actual = controller.nagios();

            assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(actual.getBody()).isEqualTo("OK");
        }

        @Test
        void shouldReturnNotOKIfDatabaseHealthCheckNotPass() {
            when(healthCheck.isHealthy()).thenReturn(false);
            var actual = controller.nagios();

            assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            assertThat(actual.getBody()).isEqualTo("Not OK");
        }
    }

    @Nested
    class Version {

        @Test
        void shouldReturnAppVersion() {
            var controller = new DiagnosticController(healthCheck, "123.321");
            var actual = controller.version();

            assertThat(actual).isEqualTo("123.321");
        }
    }

    @Nested
    class Heartbeat {

        @Test
        void shouldReturnOK() {
            assertThat(controller.heartbeat()).isEqualTo("OK");
        }
    }

    @Nested
    class Diagnosis {

        @Test
        void shouldReturnOK() {
            assertThat(controller.diagnosis()).isEqualTo("OK");
        }
    }

    @Nested
    class Index {

        @Test
        void shouldReturnEndpointMap() {
            var endpointMap = controller.index();
            assertThat(endpointMap.containsKey("endpoints")).isTrue();
        }
    }
}

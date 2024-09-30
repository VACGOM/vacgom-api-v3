package kr.co.vacgom.core.global.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.common.error.dto.CommonErrorResponse
import kr.co.vacgom.common.error.entity.ErrorEntity
import kr.co.vacgom.core.global.logger.LoggerUtils
import kr.co.vacgom.core.global.security.error.AuthError
import org.springframework.http.MediaType
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.nio.charset.StandardCharsets

@RestControllerAdvice
class AuthenticationExceptionHandler(
    private val objectMapper: ObjectMapper,
    private val loggerUtils: LoggerUtils
) {

    fun unauthorizedHandler(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, exception ->
            val errorEntity = AuthError.ERR_UNAUTHORIZED
            val traceMessage = exception.message

            loggerUtils.logErrorResponse(exception, errorEntity, traceMessage)
            writeErrorResponse(response, errorEntity)
        }
    }

    fun forbiddenHandler(): AccessDeniedHandler {
        return AccessDeniedHandler { _, response, exception ->
            val errorEntity = AuthError.ERR_FORBIDDEN
            val traceMessage = exception.message

            loggerUtils.logErrorResponse(exception, errorEntity, traceMessage)
            writeErrorResponse(response, errorEntity)
        }
    }

    private fun writeErrorResponse(
        response: HttpServletResponse,
        errorEntity: ErrorEntity,
    ) {
        val errorResponse = CommonErrorResponse(errorEntity)

        response.status = errorEntity.httpStatus
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()

        response.writer.use { it.write(objectMapper.writeValueAsString(errorResponse)) }
    }
}

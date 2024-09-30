package kr.co.vacgom.core.global.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.common.error.dto.CommonErrorResponse
import kr.co.vacgom.core.global.logger.LoggerUtils
import kr.co.vacgom.core.global.security.error.AuthError
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import kotlin.text.Charsets.UTF_8

@Component
class UnauthorizedHandler(
    private val objectMapper: ObjectMapper,
    private val loggerUtils: LoggerUtils
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val errorEntity = AuthError.FORBIDDEN
        val errorMessage = authException.localizedMessage

        loggerUtils.logErrorMessage(
            exception = authException,
            errorEntity = errorEntity,
            errorMessage = errorMessage
        )

        val errorResponse = CommonErrorResponse(errorEntity)

        response.status = errorEntity.httpStatus
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = UTF_8.name()
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
        response.writer.flush()
    }
}
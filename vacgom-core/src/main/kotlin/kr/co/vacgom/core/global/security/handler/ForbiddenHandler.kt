package kr.co.vacgom.core.global.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.common.error.exception.BusinessException
import kr.co.vacgom.core.global.logger.LoggerUtils
import kr.co.vacgom.core.global.security.error.AuthError
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler


class ForbiddenHandler(
    private val loggerUtils: LoggerUtils
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException
    ) {
        val errorEntity = AuthError.FORBIDDEN

        loggerUtils.logErrorMessage(
            exception = accessDeniedException,
            errorEntity = errorEntity,
            errorMessage = accessDeniedException.localizedMessage
        )

        throw BusinessException(errorEntity)
    }
}
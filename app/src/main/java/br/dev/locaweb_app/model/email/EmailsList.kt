package br.dev.locaweb_app.model.email

import br.dev.locaweb_app.model.Pageable
import br.dev.locaweb_app.model.Sort

data class EmailsList(
    val content: MutableList<EmailData>,
    val pageable: Pageable,
    val last: Boolean,
    val totalElements: Int,
    val totalPages: Int,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val first: Boolean,
    val numberOfElements: Int,
    val empty: Boolean
)

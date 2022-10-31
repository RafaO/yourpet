package com.keller.yourpet.shared.model

import com.keller.yourpet.shared.mock.mockPetsList
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterTests {

    @Test
    fun `when applied to list - filters it properly`() {
        // given
        val list = mockPetsList()
        val filter = Filter.everything()

        // when
        val result = filter.applyTo(list)

        // then
        assertEquals(list, result)
    }
}

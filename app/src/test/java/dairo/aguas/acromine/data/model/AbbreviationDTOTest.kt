package dairo.aguas.acromine.data.model

import dairo.aguas.acromine.mock.Mocks
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class AbbreviationDTOTest {

    @Test
    fun givenAbbreviationDTOShouldMapToDomain() {
        val abbreviationDTO = Mocks.MOCK_ABBREVIATION_DTO
        val abbreviationDomain = abbreviationDTO.toDomainLongForms()
        val fullForm = abbreviationDomain.fullForms.first()
        val variationObject = fullForm.variationObjects.first()

        assertEquals("sf", abbreviationDomain.sf)
        assertEquals(200, fullForm.freq)
        assertEquals("lf", fullForm.lf)
        assertEquals(2002, fullForm.since)
        assertEquals(100, variationObject.freq)
        assertEquals("lf", variationObject.lf)
        assertEquals(2001, variationObject.since)

    }
}
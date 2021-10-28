package dairo.aguas.acromine.ui.model

import dairo.aguas.acromine.mock.Mocks
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class SearchViewDataTest {

    @Test
    fun givenDomainFullFormShouldMapToSearchViewData() {
        val domainFullForm = Mocks.MOCK_FULL_FORM
        val searchViewData = SearchViewData(domainFullForm)
        val variationViewData = searchViewData.variations.first()

        assertEquals("lf", searchViewData.definitionName)
        assertEquals("1", searchViewData.totalVariations)
        assertEquals("100", searchViewData.freq)
        assertEquals("2000", searchViewData.since)

        assertEquals(100, variationViewData.freq)
        assertEquals("lf", variationViewData.definitionName)
        assertEquals(1900, variationViewData.since)

    }
}
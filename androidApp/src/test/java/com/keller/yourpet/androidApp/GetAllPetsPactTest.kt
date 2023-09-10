package com.keller.yourpet.androidApp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.PactTestExecutionContext
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit.ConsumerPactTest
import au.com.dius.pact.core.model.RequestResponsePact
import com.apollographql.apollo3.ApolloClient
import com.keller.yourpet.androidApp.utils.MainCoroutineRule
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Filter
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule

class GetAllPetsPactTest : ConsumerPactTest() {

    // TODO this is part of CoroutineTests but we cannot inherit from both
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    override fun providerName() = "YourPet API"

    override fun consumerName() = "YourPet Mobile client"

    override fun createPact(builder: PactDslWithProvider): RequestResponsePact {

        return builder
            .uponReceiving("All pets query with all fields")
            .path("/")
            .method("POST")
            .headers(mapOf("Content-Type" to "application/json"))
            .body(
                PactDslJsonBody()
                    .stringValue("operationName", "GetAllPets")
                    .stringValue(
                        "query",
                        "query GetAllPets(\$filter: Filter!) { pets(filter: \$filter) { id name imageUrl gender description } }"
                    )
                    .`object`("variables")
                    .`object`("filter")
                    .array("genders")
                    .stringValue("Male")
                    .stringValue("Female")
            )
            .willRespondWith()
            .status(200)
            .body(
                """
                {
                  "data": {
                    "pets": [
                  {
                    "id": "1",
                    "name": "Fatality",
                    "gender": "Female",
                    "imageUrl": "https://picsum.photos/id/237/200/150",
                    "description": "Fatality is a great dog"
                  },
                  {
                    "id": "2",
                    "name": "Charlie",
                    "gender": "Male",
                    "imageUrl": "https://picsum.photos/id/1025/200/150",
                    "description": "Charlie is a bit naughty"
                  }
                    ]
                  }
                }
                """
            ).toPact()
    }

    override fun runTest(mockServer: MockServer, context: PactTestExecutionContext) = runBlocking {
        val response = PetsApiClient(ApolloClient(mockServer.getUrl())).getPets(Filter.everything())
        assertEquals(response, mockPetsList())
    }
}

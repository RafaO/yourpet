//
//  PetsViewModelTests.swift
//  iosAppTests
//
//  Created by Rafael Ortega on 11/9/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import XCTest
import Foundation
@testable import iosApp
import shared

class PetsViewModelStateTests: XCTestCase {
    private let genders  = [
        Gender_.female: true,
        Gender_.male: true,
    ]

    func testState() throws {

        let petMock = Pet(id: "", name: "", imageUrl: "", gender: Gender_.female, description: "")
        let errorMessage = "pets not found"

        let testValues = [
            BaseFlowableUseCaseResultSuccess<NSArray>(result: [petMock]):
            PetsScreenState.content([petMock]),

            BaseFlowableUseCaseResultFailure<AnyObject>(error: KotlinException(message: errorMessage)):
            PetsScreenState.error(errorMessage),
        ]

        for (useCaseResult, expectedState) in testValues {
            // given
            let useCaseMock = GetPetsUseCaseMock()

            useCaseMock.useCaseResult = (FlowUtilsKt.createCFlow(initialValue: useCaseResult) as! CFlow<BaseFlowableUseCaseResult<AnyObject>>)
            let subject = PetsViewModel(getPetsUseCase: useCaseMock)

            // when
            subject.viewCreated(genders: genders)

            // then
            let statePublisher = subject.$state.collectNext(1)
            let state = try awaitPublisher(statePublisher).first
            XCTAssertTrue(state == expectedState)
        }
    }
}

class PetsViewModelTests: XCTestCase {
    
    private let genders  = [
        Gender_.female: true,
        Gender_.male: true,
    ]
    
    func testWhenViewCreatedUseCaseIsExecuted() {
        // given
        let useCaseMock = GetPetsUseCaseMock()
        let subject = PetsViewModel(getPetsUseCase: useCaseMock)
        
        // when
        subject.viewCreated(genders: genders)
        
        // then
        XCTAssertTrue(useCaseMock.executed)
    }
    
    func testInitialStateIsLoading() throws {
        // given
        let useCaseMock = GetPetsUseCaseMock()
        let subject = PetsViewModel(getPetsUseCase: useCaseMock)
        
        // when
        subject.viewCreated(genders: genders)
        
        // then
        let statePublisher = subject.$state.collectInitial()
        let state = try awaitPublisher(statePublisher)
        XCTAssertEqual(state, PetsScreenState.loading)
    }
}

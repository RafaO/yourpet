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
    private var useCaseResult: BaseFlowableUseCaseResult<NSArray>? = nil
    private var expectedState: PetsScreenState? = nil
    
    private let genders  = [
        Gender_.female: true,
        Gender_.male: true,
    ]

    override open class var defaultTestSuite: XCTestSuite {
        let petMock = Pet(name: "", imageUrl: "", gender: Gender_.female)
        let errorMessage = "pets not found"
        let testSuite = XCTestSuite(name: NSStringFromClass(self))
        addTestsWithArray(
            BaseFlowableUseCaseResultSuccess<NSArray>(result: [petMock]),
            expected: PetsScreenState.content([petMock]),
            toTestSuite: testSuite
        )
        addTestsWithArray(
            BaseFlowableUseCaseResultFailure(error: KotlinException(message: errorMessage)),
            expected: PetsScreenState.error(errorMessage),
            toTestSuite: testSuite
        )
        return testSuite
    }

    private class func addTestsWithArray(_ result: BaseFlowableUseCaseResult<NSArray>,
                                         expected: PetsScreenState,
                                         toTestSuite testSuite: XCTestSuite) {
        testInvocations.forEach { invocation in
            let testCase = PetsViewModelStateTests(invocation: invocation)
            testCase.useCaseResult = result
            testCase.expectedState = expected
            testSuite.addTest(testCase)
        }
    }
    
    func testState() throws {
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

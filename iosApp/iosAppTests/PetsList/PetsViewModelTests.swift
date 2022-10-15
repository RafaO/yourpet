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

class GetPetsUseCaseMock: GetPetsUseCaseInterface {
    var executed: Bool = false
    
    var useCaseResult: CFlow<BaseFlowableUseCaseResult<AnyObject>>? = nil
    
    func invoke(param: Any?, completionHandler: @escaping (CFlow<BaseFlowableUseCaseResult<AnyObject>>?, Error?) -> Void) {
        executed = true
        
        completionHandler(useCaseResult, nil)
    }
    
    func performAction(param: Any?, completionHandler: @escaping (Kotlinx_coroutines_coreFlow?, Error?) -> Void) {
        
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
    
    func testWhenUseCaseSucceedsStateIsContent() throws {
        // given
        let useCaseMock = GetPetsUseCaseMock()
        let petMock = Pet(name: "", imageUrl: "", gender: Gender_.female)
        
        let useCaseResult = BaseFlowableUseCaseResultSuccess<NSArray>(result: [petMock])
        useCaseMock.useCaseResult = (FlowUtilsKt.createCFlow(initialValue: useCaseResult) as! CFlow<BaseFlowableUseCaseResult<AnyObject>>)
        let subject = PetsViewModel(getPetsUseCase: useCaseMock)

        // when
        subject.viewCreated(genders: genders)

        // then
        let statePublisher = subject.$state.collectNext(1)
        let state = try awaitPublisher(statePublisher).first
        let expected = PetsScreenState.content([petMock])
        XCTAssertTrue(state == expected)
    }
}

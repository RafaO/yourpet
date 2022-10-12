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
    
    func invoke(param: Any?, completionHandler: @escaping (CFlow<BaseFlowableUseCaseResult<AnyObject>>?, Error?) -> Void) {
        executed = true
    }
    
    func performAction(param: Any?, completionHandler: @escaping (Kotlinx_coroutines_coreFlow?, Error?) -> Void) {
        
    }
}

class PetsViewModelTests: XCTestCase {
    
    
    func testWhenViewCreatedUseCaseIsExecuted() {
        // given
        let genders = [
            Gender_.female: true,
            Gender_.male: true,
        ]
        let useCaseMock = GetPetsUseCaseMock()
        let subject = PetsViewModel(getPetsUseCase: useCaseMock)
        
        // when
        subject.viewCreated(genders: genders)
        
        // then
        XCTAssertTrue(useCaseMock.executed)
    }
}

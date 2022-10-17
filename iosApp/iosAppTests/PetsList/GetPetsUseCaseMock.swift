//
//  GetPetsUseCaseMock.swift
//  iosAppTests
//
//  Created by Rafael Ortega on 17/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

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

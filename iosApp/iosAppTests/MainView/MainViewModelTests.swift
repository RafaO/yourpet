//
//  MainViewModelTests.swift
//  iosAppTests
//
//  Created by Rafael Ortega on 10/9/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import XCTest
import Foundation
import SwiftUI
@testable import iosApp

class MainVieModelTests: XCTestCase {
    
    func testWhenColorSchemeChangedNewStateIsGenerated() {
        // given
        let subject = MainViewModel()
        
        // when
        subject.colorSchemeSelected(selected: ColorScheme.dark)
        
        // then
        XCTAssert(subject.state.colorScheme == ColorScheme.dark)
    }
        
}

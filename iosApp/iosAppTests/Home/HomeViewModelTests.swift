//
//  HomeViewModelTests.swift
//  iosAppTests
//
//  Created by Rafael Ortega Lupion on 22/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import XCTest
import Foundation
@testable import iosApp

class HomeViewModelTests: XCTestCase {
    func testWhenMenuOptionSelectedThenMenuIsHidden() {
        // given
        let viewModel = HomeViewModel()
        
        // when
        viewModel.selectedOption(newOption: MenuOption.Pets)
        
        // then
        XCTAssertFalse(viewModel.showMenu)
    }
}

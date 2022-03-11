//
//  SwiftExtensions.swift
//  iosApp
//
//  Created by Rafael Ortega on 11/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension Array where Element: AnyObject{
    init(_ kotlinArray: KotlinArray<Element>){
        self.init()
        let iterator = kotlinArray.iterator()
        while iterator.hasNext() {
            self.append(iterator.next_() as! Element)
        }
    }
}

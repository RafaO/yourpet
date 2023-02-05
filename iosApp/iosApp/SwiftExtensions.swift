//
//  SwiftExtensions.swift
//  iosApp
//
//  Created by Rafael Ortega on 11/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension Array where Element: AnyObject{
    init(_ kotlinArray: KotlinArray<Element>){
        self.init()
        let iterator = kotlinArray.iterator()
        while iterator.hasNext() {
            self.append(iterator.next_() as! Element)
        }
    }
}

func ??<T>(lhs: Binding<Optional<T>>, rhs: T) -> Binding<T> {
    Binding(
        get: { lhs.wrappedValue ?? rhs },
        set: { lhs.wrappedValue = $0 }
    )
}

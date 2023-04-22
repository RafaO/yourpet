import Combine
import shared
import SwiftUI

class HomeViewModel: ObservableObject {
    @Published private(set) var selectedOption = MenuOption.Pets
    
    @Published var genders = [
        Gender_.female: true,
        Gender_.male: true,
    ]
    
    @Published var showMenu = false
//    {
//        didSet {
//            if (!showMenu) {
//                viewCreated()
//            }
//        }
//    }
    
    func selectedOption(newOption: MenuOption) {
        selectedOption = newOption
        withAnimation {
            showMenu = false
        }
    }
}

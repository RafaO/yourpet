import SwiftUI
import shared

struct HomeView: View {
    @ObservedObject var viewModel: HomeViewModel
    private let mainViewModel: MainViewModel
    
    private let petsScreen: PetsScreen
    
    init (homeViewModel: HomeViewModel, mainViewModel: MainViewModel){
        self.viewModel = homeViewModel
        let databaseDriverFactory = DatabaseDriverFactory()
        let database = DatabaseModule().createDataBase(driver: databaseDriverFactory.createDriver())
        let databaseHelper = PetsDataBaseHelper(database: database)
        
        let useCase = GetPetsUseCase(repository: PetsRepository(
            cacheSource: databaseHelper,
            networkSource: PetsApiClient()
        ))
        
        self.petsScreen = PetsScreen(
            viewModel: PetsViewModel(getPetsUseCase: useCase),
            homeViewModel: homeViewModel
        )
        
        self.mainViewModel = mainViewModel
    }
    
    @ViewBuilder
    func content() -> some View {
        switch viewModel.selectedOption {
        case .Pets:
            petsScreen
        case .Settings:
            SettingsView(mainViewModel: mainViewModel)
        }
    }
    
    var body: some View {
        let drag = DragGesture()
            .onEnded {
                if $0.translation.width < -100 {
                    withAnimation {
                        viewModel.showMenu = false
                    }
                }
            }
        
        if (viewModel.selectedOption == .Pets && !viewModel.showMenu) {
            petsScreen.menuHidden()
        }
        
        return NavigationView {
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    content().customNavigationTitle(with: "YourPet")
                    .offset(x: viewModel.showMenu ? geometry.size.width/2 : 0)
                    .disabled(viewModel.showMenu)
                    if viewModel.showMenu {
                        MenuView(viewModel: viewModel)
                            .frame(width: geometry.size.width/2)
                            .transition(.move(edge: .leading))
                    }
                }.gesture(drag)
            }
            .navigationBarItems(leading: (
                Button(action: {
                    withAnimation {
                        viewModel.showMenu.toggle()
                    }
                }) {
                    Image(systemName: "line.horizontal.3")
                        .imageScale(.large)
                }
            ))
        }
    }
}

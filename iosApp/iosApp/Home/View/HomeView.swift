import SwiftUI
import shared

struct HomeView: View {
    @ObservedObject var viewModel: HomeViewModel
    
    @ViewBuilder
    func content() -> some View {
        switch viewModel.selectedOption {
        case .Pets:
            let databaseDriverFactory = DatabaseDriverFactory()
            let database = DatabaseModule().createDataBase(driver: databaseDriverFactory.createDriver())
            let databaseHelper = PetsDataBaseHelper(database: database)
            
            let useCase = GetPetsUseCase(repository: PetsRepository(cacheSource: databaseHelper, networkSource: PetsApiClient()))
            PetsScreen(viewModel: PetsViewModel(getPetsUseCase: useCase,
                                                genders: viewModel.genders))
        case .Settings:
            SettingsView()
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
        
        return NavigationView {
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    content()
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

import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: PetsViewModel
    
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
                    Group{
                        switch viewModel.state {
                        case .loading:
                            Text("Loading..")

                        case .content (let content): PetsListView(content: content)

                        case .error(let message): PetsListErrorView(message: message) { viewModel.viewCreated() }
                        }
                    }.onAppear {
                        viewModel.viewCreated()
                    }
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

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}

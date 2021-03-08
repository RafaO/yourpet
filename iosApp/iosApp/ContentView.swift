import SwiftUI
import shared

struct ContentView: View {
    @State var textToDisplay = Text("loading...")
    
    var body: some View {
        Group{
            textToDisplay
        }.onAppear {
            
            GetPetsUseCase(repository: PetsRepository(cacheSource:PetsDataBase(),
                                                      networkSource: PetsApiClient())).execute { pets,_ in
                                                        pets?.watch { (pets) in
                                                            self.textToDisplay = Text((pets![0] as! Pet).name)
                                                        }
                                                      }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

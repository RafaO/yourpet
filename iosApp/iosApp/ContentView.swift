import SwiftUI
import shared

struct ContentView: View {
    @State var textToDisplay = Text("loading...")
    
    var body: some View {
        Group{
            textToDisplay
        }.onAppear {
            GetPetsUseCase().execute(completionHandler: {(pets: [Pet]?, _: Error?) in
                self.textToDisplay = Text(pets![0].name)
            })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

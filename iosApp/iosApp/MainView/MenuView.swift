//
//  MenuView.swift
//  iosApp
//
//  Created by Rafael Ortega on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MenuView: View {
    var body: some View {
        VStack(alignment: .leading) {
            Text("Genders")
                .foregroundColor(.gray)
                .font(.headline)
                .padding(.top, 100)
            ForEach(Array(Gender_.values()), id: \.name) { gender in
                Text(gender.name)
                    .foregroundColor(.gray)
            }
            Spacer()
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(red: 32/255, green: 32/255, blue: 32/255))
        .edgesIgnoringSafeArea(.all)
    }
}

//struct MenuView_Previews: PreviewProvider {
//    static var previews: some View {
//        MenuView()
//    }
//}

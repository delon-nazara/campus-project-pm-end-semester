package com.example.proyekakhirpemrogramanmobile

data class DetailDaftarModul(
    var imageRes : Int,
    var namaModul : String,
)

fun GetDaftarModul(): List<DetailDaftarModul>{
    return listOf<DetailDaftarModul>(
        DetailDaftarModul(R.drawable.icon_pdf,"Mod1-PengantarGrafika.pdf\n"),
        DetailDaftarModul(R.drawable.pdf_icon,"Mod2-Primitive Drawing-Alg..."),
        DetailDaftarModul(R.drawable.icon_pdf,"Mod3 dan 4-Primitive Draw..."),
        DetailDaftarModul(R.drawable.icon_pdf,"Mod5-Output Primitive Drawi ..."),
    )
}
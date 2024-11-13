//package com.example.proyekakhirpemrogramanmobile
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.proyekakhirpemrogramanmobile.ui.theme.ProyekAkhirPemrogramanMobileTheme
//
//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun ListDemo() {
//    LazyColumnDemo()
//}
//
//@Composable
//fun LazyColumnDemo(modifier: Modifier = Modifier) {
//    var mylist = getModulDetail()
//    LazyColumn(content = {
//        itemsIndexed(mylist, itemContent = {index, item->
//            CardModul(item = item)
//        })
//    })
//
//}
//
//
////=====================
////KONTAINER KARTU MODUL
////=====================
//@Composable
//fun CardModul(item : DetailModul, modifier : Modifier = Modifier) {
//    Card(
//        onClick = {},
//        colors = CardDefaults.cardColors(
//            containerColor = colorResource(R.color.very_light_blue),
//        ),
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .size(height = 100.dp, width = 100.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 16.dp)
//                .padding(bottom = 16.dp)
//                .padding(end = 16.dp), // Menambah padding untuk isi card
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Image(
//                painter = painterResource(id = item.imageRes),
//                contentDescription = item.modulName,
//                modifier = Modifier
//                    .size(100.dp)
//                    .padding(end = 8.dp)
//            )
//            Column(
//                modifier = Modifier.fillMaxHeight(),
//                verticalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(
//                    text = item.modulName,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                )
//                Text(
//                    text = "Jumlah Modul : ${item.jumlahModul}",
//                    fontSize = 14.sp,
//                    color = Color.White.copy(alpha = 0.9f)
//                )
//                Text(
//                    text = "Semester : ${item.onSemester}",
//                    fontSize = 12.sp,
//                    color = Color.White.copy(alpha = 0.7f)
//                )
//            }
//        }
//    }
//    @Composable
//    fun ModulItem(item : DetailModul,modifier: Modifier = Modifier) {
//        Row(
//            modifier = Modifier.background(Color.Red).fillMaxWidth().size(height = 100.dp, width = 100.dp),
//        ){
//            Image(painter = painterResource(id = item.imageRes),
//                contentDescription = item.modulName,
//                modifier = Modifier.size(100.dp)
//            )
//            Column(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .background(Color.Blue),
//                verticalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(text = item.modulName,
//                    style = TextStyle(fontSize = 22.sp,
//                        fontWeight = FontWeight.Bold)
//                )
//                Text(text = "Semester : ${item.onSemester}",
//                    style = TextStyle(fontSize = 22.sp,
//                    )
//
//                )
//                Text(text = "Jumlah Modul : ${item.jumlahModul}",
//                    style = TextStyle(fontSize = 22.sp,
//                    )
//
//                )
//
//            }
//
//        }
//    }
//}
//
////@Composable
////fun SimpleColumn(modifier: Modifier = Modifier) {
////    val scrollState = rememberScrollState()
////    Column(
////        modifier = Modifier.verticalScroll(scrollState)
////
////    ){
////        for ( i in 1..100){
////            TextItem("item $i")
////        }
////    }
////}
////
////@Composable
////fun TextItem(text : String, modifier: Modifier = Modifier) {
////    Text(text = text,
////        modifier = Modifier
////            .fillMaxWidth()
////            .padding(16.dp),
////        textAlign = TextAlign.Center,
////        fontSize = 24.sp)
////}
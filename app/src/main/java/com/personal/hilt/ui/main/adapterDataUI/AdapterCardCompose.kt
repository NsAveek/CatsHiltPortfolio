package com.personal.hilt.ui.main.adapterDataUI

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personal.hilt.R
import com.personal.hilt.model.CatsDataResponseItem


@Composable
fun CatsList(
    catsList: ArrayList<CatsDataResponseItem>,
    modifier: Modifier = Modifier
) {

}


@Preview
@Composable
fun previewCatsList() {
    Column(modifier = Modifier.fillMaxWidth()) {
        CatsSingleCard()
        Spacer(modifier = Modifier.height(16.dp))
        CatsSingleCard()
    }
}


@Composable
fun CatsSingleCard() {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.height(200.dp).fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
            Box {
                    Image(
                        painter = painterResource(id = R.drawable.aa),
                        contentDescription = "cats",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
//                    Text(text = "Hi Cats")
//
//                    Text(text = "Hello Aveek")
                }
            }
    }
}


@Composable
fun catsDataCard(
    item: CatsDataResponseItem,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(200.dp)
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .clickable { onClicked },
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}

//@Composable
//fun catsDataList( items : Flow<PagingData<CatsDataResponseItem>>){
//    val lazyCatsItems : LazyPagingItems<CatsDataResponseItem> = items.collectAsLazyPagingItems()
//    LazyColumn{
//        items(lazyCatsItems){
//            catItem ->
//            CatDataItem(catItem = catItem)
//        }
//    }
//}
//@Composable
//fun CatDataItem(catItem = CatsDataResponseItem){
//    Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
//
//    }
//}



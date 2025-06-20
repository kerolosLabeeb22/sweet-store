package com.example.online_shope.Activity.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.online_shope.Item.ItemsModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.online_shope.Helper.ManagmentCart
import com.example.online_shope.R


@Composable
fun DetailsScreenCont(
    navController: NavHostController, item: ItemsModel,
    onBackClick: () -> Unit,
    onAddCartClick: () -> Unit,
    onCartClick: () -> Unit,
) {


    val context = LocalContext.current
    val managementCart = remember { ManagmentCart(context) }
    var selectedImage by remember { mutableStateOf(item.picUrl.first()) }
    var selectedModelIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    R.color.white
                )
            )
            .verticalScroll(rememberScrollState())
    ) {

    }
}

@Composable
fun HeaderSection(
    selectedImageUrl: String,
    imageUrls: List<String>,
    onBackClick: () -> Unit,
    onImageClick: (String) -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
            .padding(bottom = 16.dp)
    ) {
        val (back, fav, mainImage, thumbnail) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(model = selectedImageUrl),
            contentDescription = "Selected Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(8.dp)
                )
                .constrainAs(mainImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        BackButton(onBackClick, Modifier.constrainAs(back) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        FavoriteButton(Modifier.constrainAs(fav) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })

    }

}

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.back),
        contentDescription = "Back",
        modifier = modifier
            .padding(16.dp)
            .clickable { onClick() }
    )

}

@Composable
fun FavoriteButton(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.fav_icon),
        contentDescription = "Favorite",
        modifier = modifier
            .padding(16.dp)
    )
}
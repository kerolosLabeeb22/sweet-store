package com.example.online_shope.Activity.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.online_shope.Helper.ManagmentCart
import com.example.online_shope.Item.ItemsModel
import com.example.online_shope.R


@Composable
fun CartList(
    cartItems: ArrayList<ItemsModel>,
    nmanagmentCart: ManagmentCart,
    onItemChange: () -> Unit,

) {
    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        items(cartItems) { item ->
            CartItem(
                cartItems,
                item = item,
                managmentCart = nmanagmentCart,
                onItemChange = onItemChange
            )
        }
    }
}

@Composable
fun CartItem(
    cartItem: ArrayList<ItemsModel>,
    item: ItemsModel,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        val (pic, titleTxt, feeEachItem, totalEachItem, Quantity) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(item.picUrl[0]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(90.dp)
                .background(
                    color = colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(pic) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = item.title,
            modifier = modifier
                .constrainAs(titleTxt) {
                    start.linkTo(pic.end)
                    top.linkTo(parent.top)
                }
                .padding(start = 8.dp, top = 8.dp)
        )

        Text(
            text = "$${item.price}",
            color = colorResource(R.color.green),
            modifier = modifier
                .constrainAs(feeEachItem) {
                    start.linkTo(titleTxt.start)
                    top.linkTo(titleTxt.bottom)
                }
                .padding(start = 8.dp, top = 8.dp)
        )

        Text(
            text = "$${item.numberInCart * item.price}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .constrainAs(totalEachItem) {
                    start.linkTo(titleTxt.start)
                    bottom.linkTo(pic.bottom)
                }
                .padding(start = 8.dp)

        )

        ConstraintLayout(
            modifier = modifier
                .width(100.dp)
                .constrainAs(Quantity) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(100.dp)
                )) {
            val (plusCartBtn, minusCartBtn, numberItemText) = createRefs()

            Text(
                text = item.numberInCart.toString(),
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
                modifier = modifier.constrainAs(numberItemText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
            )

            Box(
                modifier = modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        colorResource(R.color.green),
                        shape = RoundedCornerShape(100.dp)

                    )
                    .constrainAs(plusCartBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        managmentCart.plusItem(
                            cartItem,
                            cartItem.indexOf(item)
                        ) {
                            onItemChange()
                        }
                    }
            ) {
                Text(
                    text = "+",
                    color = colorResource(R.color.white),
                    modifier = modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center

                )
            }

            Box(
                modifier = modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        colorResource(R.color.white),
                        shape = RoundedCornerShape(100.dp)

                    )
                    .constrainAs(plusCartBtn) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        managmentCart.minusItem(
                            cartItem,
                            cartItem.indexOf(item)
                        ) {
                            onItemChange()
                        }
                    }
            ) {
                Text(
                    text = "-",
                    color = colorResource(R.color.green),
                    modifier = modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center

                )
            }


        }

    }

}
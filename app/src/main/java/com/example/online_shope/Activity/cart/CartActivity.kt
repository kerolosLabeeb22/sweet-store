package com.example.online_shope.Activity.cart

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.online_shope.Helper.ManagmentCart
import com.example.online_shope.R

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartScreen(
                ManagmentCart(this),
                onBackClick = { finish() }
            )
        }
    }
}

@Composable
fun CartScreen(
    managmentCart: ManagmentCart = ManagmentCart(LocalContext.current),
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var cartItem = remember { mutableStateOf(managmentCart.listCart) }
    val tax = remember { mutableStateOf(0.0) }

    calculateTax(managmentCart, tax)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ConstraintLayout(modifier = modifier.padding(top = 36.dp)) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt) { centerTo(parent) }, text = "Your Cart",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = "Back",
                modifier = modifier
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .clickable { onBackClick() }

            )
        }
        if (cartItem.value.isEmpty()) {
            Text(text = "Cart is empty", modifier = modifier.align(Alignment.CenterHorizontally))

        } else {
            CartList(cartItems = cartItem.value, managmentCart) {
                cartItem.value = managmentCart.getListCart()
                calculateTax(managmentCart, tax)

            }
        }

    }


}

fun calculateTax(managmentCart: ManagmentCart, tax: MutableState<Double>) {
    val percentTax = 0.02

    tax.value = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0

}
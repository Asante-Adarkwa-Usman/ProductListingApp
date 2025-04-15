package com.usman.cvsproductsapp.ui.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.utils.UiStatus

@Composable
fun ProductDetailsSection(
    product: ProductItemModel,
    newPrice: String,
    onPriceChange: (String) -> Unit,
    onSaveClick: (Double) -> Unit,
    updatedProductState: UiStatus<ProductItemModel>
) {
    val context = LocalContext.current //get context

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Product Details",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Text("Title: ${product.title}")
        Text("Description: ${product.description}")
        Text("Current Price: $${product.price}")

        OutlinedTextField(
            value = newPrice,
            onValueChange = onPriceChange,
            label = { Text("New Price") }
        )

        Button(onClick = {
            val price = newPrice.toDoubleOrNull()
            if (price != null) onSaveClick(price)
        }) {
            Text("Save Price")
        }

        when (updatedProductState) {
            is UiStatus.Loading -> {}
            is UiStatus.Success -> {
                LaunchedEffect(updatedProductState) {
                    Toast.makeText(context, "Price updated successfully!", Toast.LENGTH_LONG).show()
                    onPriceChange("") //clear input
                }
                Text("Price has been edited!")
            }
            is UiStatus.Error -> Text("Error: ${(updatedProductState).message}")
        }
    }
}

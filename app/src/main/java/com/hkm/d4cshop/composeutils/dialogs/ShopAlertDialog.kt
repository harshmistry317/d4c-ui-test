package com.hkm.d4cshop.composeutils.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.hkm.d4cshop.R
import com.hkm.d4cshop.ui.theme.DarkBackground
import com.hkm.d4cshop.ui.theme.InStockGreen
import com.hkm.d4cshop.ui.theme.OutOfStockRed
import com.hkm.d4cshop.ui.theme.PrimaryText
import com.hkm.d4cshop.ui.theme.SecondaryText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopAlertDialog(
    message: String,
    title: String = stringResource(R.string.app_name),
    showNegativeButton: Boolean = false,
    positiveButtonText : String? = null,
    negativeButtonText : String? = null,
    onPositiveButtonClick: ()-> Unit,
    onNegativeButtonClick: () -> Unit = {}
) {

    BasicAlertDialog(
        onDismissRequest = {

        },
        modifier = Modifier,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        ),
        content = {
            Surface(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                color = DarkBackground
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500, fontSize = 24.sp, color = PrimaryText, lineHeight = 32.sp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W400, fontSize = 14.sp, color = PrimaryText, lineHeight = 20.sp,),
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ){
                        if (showNegativeButton){
                            TextButton(

                                onClick = {
                                    onNegativeButtonClick()
                                },
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = OutOfStockRed
                                )
                            ){
                                Text(text = negativeButtonText ?: "",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500, fontSize = 14.sp, color = DarkBackground, lineHeight = 20.sp,))
                            }

                            Spacer(Modifier.width(8.dp))
                        }


                        TextButton(

                            onClick = {
                                onPositiveButtonClick()
                            },
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = InStockGreen
                            )
                        ){
                            Text(text = positiveButtonText?: "",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500, fontSize = 14.sp, color = DarkBackground, lineHeight = 20.sp,))
                        }
                    }
                }


//



            }
        }
    )


}

@Preview(showBackground = true)
@Composable
fun PreviewAlertDialog(){
    MaterialTheme{
        ShopAlertDialog(
            message = "Do yo want to remove it all ?",
            title = "Empty Cart",
            showNegativeButton = true,
            positiveButtonText = "Cancel",
            negativeButtonText = "Remove All",
            onPositiveButtonClick = {

            },
            onNegativeButtonClick = {

            }
        )
    }
}
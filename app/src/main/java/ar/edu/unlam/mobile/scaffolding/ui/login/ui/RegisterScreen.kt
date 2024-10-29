package ar.edu.unlam.mobile.scaffolding.ui.login.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun loginScreen(viewModel: RegisterScreenViewModel) {
    Box(Modifier.fillMaxSize().padding(16.dp)){
        Register(Modifier.align(Alignment.Center),viewModel)
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterScreenViewModel) {
val email:String by viewModel.email.observeAsState(initial="")
    val password:String by viewModel.password.observeAsState(initial = "")
val registerEnable:Boolean by viewModel.registerEnable.observeAsState(initial = false)
val nameUser:String by viewModel.nameUser.observeAsState(initial="")

Column (modifier=modifier){
 //HeaderImage(modifier.align(Alignment.CenterHorizontally))
  Spacer(modifier = modifier.padding(16.dp))
    NameUserField(nameUser){viewModel.onRegisterChanged(it,email,password)}
    Spacer(modifier = modifier.padding(16.dp))
    EmailField(email){viewModel.onRegisterChanged(it,password,nameUser)}
    Spacer(modifier = modifier.padding(16.dp))
    PassWordField(password){viewModel.onRegisterChanged(email,nameUser,it)}
    Spacer(modifier = modifier.padding(16.dp))
    PassWordField(password){viewModel.onRegisterChanged(email,nameUser,it)}
    RegisterBotton(registerEnable){viewModel.onRegisterSelected()}

}
}

@Composable
fun RegisterBotton(registerEnable: Boolean, onRegisterSelected: () -> Unit) {
    Button(onClick = {onRegisterSelected()} ,
        modifier=Modifier.fillMaxWidth().height(40.dp),
        enabled=registerEnable
        )
    {
        Text(text="Registrarse")
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")

@Composable
fun NameUserField(nameUser: String,onTextFieldChange: (String)  -> Unit) {
    TextField(value = nameUser, onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Nombre de Usuario") },
        label = { Text("Nombre de Usuario") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun PassWordField(password:String,onTextFieldChange: (String) -> Unit) {
    TextField(value = password, onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Password") },
        label = { Text("Password") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )

}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun EmailField(email:String, onTextFieldChange: (String) -> Unit)  {

    TextField(value = email, onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Email") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )
}

/*@Composable
fun HeaderImage(modifier: Modifier) {
   Image(painter = painterResource(id= R.mipmap.ic_launcher), contentDescription = "Header")
}*/

package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.VetraButton
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraCheckboxWithLabel
import com.flyfishxu.vetraui.core.VetraOutlinedButton
import com.flyfishxu.vetraui.core.VetraRadioGroup
import com.flyfishxu.vetraui.core.VetraSwitchWithLabel
import com.flyfishxu.vetraui.core.VetraTextField
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun InputsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var search by remember { mutableStateOf("") }
    var hasError by remember { mutableStateOf(false) }

    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var soundEnabled by remember { mutableStateOf(true) }

    var selectedSize by remember { mutableStateOf("Medium") }
    var selectedColor by remember { mutableStateOf("Blue") }

    var termsAccepted by remember { mutableStateOf(false) }
    var newsletterSubscribed by remember { mutableStateOf(true) }
    var marketingEnabled by remember { mutableStateOf(false) }
    var analyticsEnabled by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Input Components",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Text fields and form controls with elegant animations",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Text Fields
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Text Fields",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Animated floating labels and expanding underlines",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    placeholder = "Enter your username"
                )

                VetraTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        hasError = it.isNotEmpty() && !it.contains("@")
                    },
                    label = "Email",
                    placeholder = "your@email.com",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Mail,
                            contentDescription = "Email"
                        )
                    },
                    isError = hasError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                if (hasError) {
                    Text(
                        "Please enter a valid email address",
                        style = typography.bodySm.copy(color = colors.danger)
                    )
                }
            }
        }

        // Password Field
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Password Field",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )

                VetraTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "Enter password",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Password"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }

        // Search Field
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Search Field",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )

                VetraTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = "Search for anything...",
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    }
                )
            }
        }

        // Disabled Field
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Disabled State",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )

                VetraTextField(
                    value = "This field is read-only",
                    onValueChange = {},
                    label = "Read Only",
                    enabled = false
                )
            }
        }

        // Switches
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Switches",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Smooth toggle animations with clear states",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraSwitchWithLabel(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    label = "Enable Notifications"
                )

                VetraSwitchWithLabel(
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it },
                    label = "Dark Mode"
                )

                VetraSwitchWithLabel(
                    checked = soundEnabled,
                    onCheckedChange = { soundEnabled = it },
                    label = "Sound Effects"
                )

                VetraSwitchWithLabel(
                    checked = true,
                    onCheckedChange = {},
                    label = "Disabled Switch",
                    enabled = false
                )
            }
        }

        // Radio Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Radio Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Single choice selection with smooth animations",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                Text(
                    "Select Size",
                    style = typography.bodyLg.copy(color = colors.textPrimary)
                )
                VetraRadioGroup(
                    options = listOf("Small", "Medium", "Large", "Extra Large"),
                    selectedOption = selectedSize,
                    onOptionSelected = { selectedSize = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Choose Color",
                    style = typography.bodyLg.copy(color = colors.textPrimary)
                )
                VetraRadioGroup(
                    options = listOf("Red", "Blue", "Green", "Purple"),
                    selectedOption = selectedColor,
                    onOptionSelected = { selectedColor = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Disabled Group",
                    style = typography.bodyLg.copy(color = colors.textPrimary)
                )
                VetraRadioGroup(
                    options = listOf("Option A", "Option B", "Option C"),
                    selectedOption = "Option B",
                    onOptionSelected = {},
                    enabled = false
                )
            }
        }

        // Checkboxes
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Checkboxes",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Multi-choice selection with checkmark animation",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )

                VetraCheckboxWithLabel(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    label = "I accept the terms and conditions"
                )

                VetraCheckboxWithLabel(
                    checked = newsletterSubscribed,
                    onCheckedChange = { newsletterSubscribed = it },
                    label = "Subscribe to newsletter"
                )

                VetraCheckboxWithLabel(
                    checked = marketingEnabled,
                    onCheckedChange = { marketingEnabled = it },
                    label = "Receive marketing emails"
                )

                VetraCheckboxWithLabel(
                    checked = analyticsEnabled,
                    onCheckedChange = { analyticsEnabled = it },
                    label = "Enable analytics"
                )

                VetraCheckboxWithLabel(
                    checked = true,
                    onCheckedChange = {},
                    label = "Disabled Checkbox",
                    enabled = false
                )
            }
        }

        // Indeterminate Checkbox Example
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Indeterminate State",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "Useful for hierarchical selections like 'Select All'",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var option1 by remember { mutableStateOf(true) }
                    var option2 by remember { mutableStateOf(false) }
                    var option3 by remember { mutableStateOf(false) }

                    val allChecked = option1 && option2 && option3
                    val noneChecked = !option1 && !option2 && !option3
                    val someChecked = !allChecked && !noneChecked

                    VetraCheckboxWithLabel(
                        checked = allChecked,
                        onCheckedChange = {
                            if (allChecked || someChecked) {
                                option1 = false
                                option2 = false
                                option3 = false
                            } else {
                                option1 = true
                                option2 = true
                                option3 = true
                            }
                        },
                        label = "Select All Features",
                        indeterminate = someChecked
                    )

                    Column(
                        modifier = Modifier.padding(start = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        VetraCheckboxWithLabel(
                            checked = option1,
                            onCheckedChange = { option1 = it },
                            label = "Feature 1"
                        )
                        VetraCheckboxWithLabel(
                            checked = option2,
                            onCheckedChange = { option2 = it },
                            label = "Feature 2"
                        )
                        VetraCheckboxWithLabel(
                            checked = option3,
                            onCheckedChange = { option3 = it },
                            label = "Feature 3"
                        )
                    }
                }
            }
        }

        // Form Example
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "Complete Form",
                        style = typography.headingLg.copy(color = colors.textPrimary)
                    )
                    Text(
                        "All components work together seamlessly",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    var formName by remember { mutableStateOf("") }
                    var formPhone by remember { mutableStateOf("") }
                    var formContactMethod by remember { mutableStateOf("Email") }
                    var formTermsAccepted by remember { mutableStateOf(false) }
                    var formNewsletterSubscribed by remember { mutableStateOf(true) }

                    VetraTextField(
                        value = formName,
                        onValueChange = { formName = it },
                        label = "Full Name",
                        placeholder = "John Doe"
                    )

                    VetraTextField(
                        value = formPhone,
                        onValueChange = { formPhone = it },
                        label = "Phone",
                        placeholder = "+1 (555) 123-4567",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Text(
                        "Preferred Contact Method",
                        style = typography.bodyLg.copy(color = colors.textPrimary)
                    )
                    VetraRadioGroup(
                        options = listOf("Email", "Phone", "SMS"),
                        selectedOption = formContactMethod,
                        onOptionSelected = { formContactMethod = it }
                    )

                    VetraCheckboxWithLabel(
                        checked = formTermsAccepted,
                        onCheckedChange = { formTermsAccepted = it },
                        label = "I agree to the terms and conditions"
                    )

                    VetraSwitchWithLabel(
                        checked = formNewsletterSubscribed,
                        onCheckedChange = { formNewsletterSubscribed = it },
                        label = "Subscribe to newsletter"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraOutlinedButton(
                            onClick = {
                                formName = ""
                                formPhone = ""
                                formContactMethod = "Email"
                                formTermsAccepted = false
                                formNewsletterSubscribed = true
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Cancel")
                        }
                        VetraButton(
                            onClick = {
                                // Form submit action
                                println("Form submitted: $formName, $formPhone, $formContactMethod")
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Submit")
                        }
                    }
                }
            }
        }
    }
}


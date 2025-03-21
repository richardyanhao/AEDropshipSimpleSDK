# AEDropshipSimpleSDK

Welcome to **AEDropshipSimpleSDK**! This project provides a simple and efficient SDK for integrating with the AliExpress Open Platform specifically for dropshipping (DS) business scenarios.

## Table of Contents

- [Background](#background)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Problem Solved](#problem-solved)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Background

Despite the robust capabilities of the AliExpress Open Platform, there was a noticeable absence of an official SDK tailored to dropshipping business scenarios, as well as a lack of official Maven packages. **AEDropshipSimpleSDK** was developed to fill this gap, providing developers with a straightforward and reliable means to connect to AliExpress's Open Platform.

## Features

- **Comprehensive API Coverage**: Provides extensive support for AliExpress dropshipping functionalities, including creating orders, querying order status, and tracking shipments.

- **Easy Configuration**: Simplifies the setup process with a straightforward configuration file, allowing developers to quickly get started.

- **Robust Security**: Implements secure communication protocols with built-in request signing to ensure data integrity and confidentiality.

- **Modular Design**: Offers a clean, modular codebase that is easy to understand and extend, allowing developers to customize and expand functionality as needed.

- **Error Handling and Logging**: Equipped with effective error handling mechanisms and logging capabilities to facilitate debugging and monitoring.

- **Active Community Support**: Benefit from active contributions and support from a growing community of developers using the SDK.

## Installation

To install this SDK, follow these steps:

```bash
# Clone the repository
git clone https://github.com/richardyanhao/AEDropshipSimpleSDK.git

# Navigate into the project directory
cd AEDropshipSimpleSDK
```

# Configuration
Before using the SDK, ensure you have configured your config.properties file correctly. This file should include essential configuration details, such as:
```
client_id=YOUR_CLIENT_ID
client_secret=YOUR_CLIENT_SECRET
```

# Usage
```
import ds.ae.richard.simplesdk.api.AeDropShipperClient;

public class Main {
    public static void main(String[] args) {
        AeDropShipperClient client = new AeDropShipperClient();
        // Example: Query product details
        String result = client.getProductDetails("yourAccessToken", "productId", "shipToCountry", "targetCurrency", "targetLanguage", false);
        System.out.println(result);
    }
}
```

# Problem Solved
AEDropshipSimpleSDK addresses several key issues:
- Lack of Official SDK: Provides a dedicated SDK for DS business scenarios not covered by AliExpress's existing offerings.
- Ease of Use: Simplifies the process of integrating with AliExpress's API, making it accessible even for those without extensive technical expertise.
- Centralized API Management: Offers a modular design that enhances code maintainability and readability.

# Contributing
We welcome contributions! Please read our Contributing Guidelines before submitting a Pull Request.
# License
This project is licensed under the MIT License. See the LICENSE file for more details.
# Contact
For any questions or suggestions, please contact us at 569507879@qq.com.

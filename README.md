# Locaweb App

## Descrição

O Locaweb App é um aplicativo Android desenvolvido com Kotlin e Jetpack Compose para gerenciar e enviar e-mails de forma intuitiva e eficiente. Com uma interface moderna e responsiva, o aplicativo permite que os usuários se registrem, façam login, enviem e recebam e-mails, além de gerenciar suas configurações de perfil e regras de controle de spam.

## Funcionalidades

- **Registro e Login**: Usuários podem criar uma conta e fazer login para acessar suas funcionalidades.
- **Envio de E-mails**: Permite que os usuários enviem e-mails para outros usuários.
- **Gerenciamento de E-mails**: Visualização de e-mails recebidos e enviados, com a possibilidade de deletar e-mails.
- **Interface Amigável**: Design moderno utilizando Jetpack Compose para uma experiência de usuário fluida.
- **Gerenciamento de Perfil**: Usuários podem editar suas informações pessoais.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal do aplicativo.
- **Jetpack Compose**: Biblioteca para construção de interfaces de usuário.
- **Retrofit**: Biblioteca para comunicação com APIs RESTful.
- **Coroutines**: Para gerenciamento de tarefas assíncronas.
- **MVVM**: Padrão de arquitetura utilizado para separar a lógica de negócios da interface do usuário.

## Estrutura do Projeto

- `app/src/main/java/br/dev/locaweb_app`: Contém a lógica principal do aplicativo, incluindo atividades, modelos e serviços.
- `app/src/main/res`: Contém os recursos do aplicativo, como layouts, strings e temas.
- `gradle`: Configurações do Gradle para gerenciamento de dependências e construção do projeto.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu_usuario/locaweb-app.git
   ```

2. Abra o projeto no Android Studio.

3. Certifique-se de que o emulador ou dispositivo Android esteja configurado.

4. **Importante**: Para o aplicativo funcionar, é necessário que a API esteja em execução. Para configurar a API, siga os passos descritos em [locaweb-api-kotlin](https://github.com/viniciusleonel/locaweb-fiap).

5. Execute o aplicativo a partir do Android Studio.

## Telas do aplicativo:

*Adicione aqui os prints da tela do aplicativo:*

![Telas do APP](./public\images\screens.jpg)

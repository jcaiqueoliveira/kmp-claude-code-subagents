# Rádios Brasil - KMP Radio Player

Um aplicativo multiplataforma de radio player para Android e iOS, desenvolvido com Kotlin Multiplatform e Compose Multiplatform.

## Características

- 📻 Reprodução de rádios ao vivo via streaming
- ⭐ Sistema de favoritos
- 🔍 Busca de estações
- 🎵 Filtros por gênero e localização
- 🎨 Interface moderna com Material Design 3
- 📱 Suporte para Android e iOS
- 🎯 Código compartilhado entre plataformas

## Arquitetura

O projeto utiliza uma arquitetura limpa e moderna:

### Camadas

1. **Data Layer** (`data/`)
   - `model/`: Modelos de dados (RadioStation, PlayerState, etc)
   - `remote/`: Serviços de API (RadioApiService)
   - `repository/`: Repositório para gerenciar dados (RadioRepository)

2. **Domain Layer** (`domain/`)
   - AudioPlayer: Interface multiplataforma para reprodução de áudio
   - Implementações específicas para Android (ExoPlayer) e iOS (AVPlayer)

3. **Presentation Layer** (`presentation/`)
   - ViewModel: Gerenciamento de estado
   - UI: Componentes Compose Multiplatform

### Tecnologias

- **Kotlin Multiplatform**: Compartilhamento de código entre Android e iOS
- **Compose Multiplatform**: UI declarativa multiplataforma
- **Ktor**: Cliente HTTP para networking
- **Kotlinx Serialization**: Serialização JSON
- **Multiplatform Settings**: Armazenamento de dados local
- **Media3 (Android)**: Player de áudio para Android
- **AVFoundation (iOS)**: Player de áudio para iOS

## Estrutura do Projeto

```
radioplayerbr/
├── shared/                          # Código compartilhado
│   ├── src/
│   │   ├── commonMain/             # Código comum
│   │   │   └── kotlin/
│   │   │       └── br/com/radioplayerbr/
│   │   │           ├── data/       # Camada de dados
│   │   │           ├── domain/     # Lógica de negócio
│   │   │           └── presentation/ # ViewModels e UI
│   │   ├── androidMain/            # Código específico Android
│   │   └── iosMain/                # Código específico iOS
│   └── build.gradle.kts
├── androidApp/                      # Aplicativo Android
│   ├── src/main/
│   │   ├── kotlin/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── iosApp/                          # Aplicativo iOS
    ├── ContentView.swift
    ├── RadioPlayerApp.swift
    └── Info.plist
```

## Funcionalidades

### Principais

- **Lista de Rádios**: Exibe todas as estações disponíveis
- **Reprodução**: Toque em uma estação para começar a ouvir
- **Mini Player**: Controles de reprodução sempre visíveis
- **Favoritos**: Marque suas estações favoritas
- **Busca**: Encontre rádios por nome, gênero ou localização

### Dados Mock

O aplicativo vem com 10 estações de rádio pré-configuradas incluindo:
- Rádio Globo Rio
- Jovem Pan FM
- Antena 1 FM
- 89 FM - A Rádio Rock
- Transamérica Pop
- E mais...

Você pode substituir os dados mock por uma API real editando `RadioApiService.kt`.

## Requisitos

### Android
- Android Studio Arctic Fox ou superior
- JDK 17
- Android SDK 24+

### iOS
- Xcode 14+
- macOS com suporte a M1/M2 ou Intel
- CocoaPods

## Como Executar

### Android

1. Abra o projeto no Android Studio
2. Aguarde o Gradle sync completar
3. Execute o módulo `androidApp`

```bash
./gradlew :androidApp:installDebug
```

### iOS

1. Abra o projeto no Xcode navegando até `iosApp/`
2. Execute o build do framework shared primeiro:

```bash
./gradlew :shared:embedAndSignAppleFrameworkForXcode
```

3. Abra o projeto iOS no Xcode e execute

## Customização

### Adicionar mais rádios

Edite o arquivo `shared/src/commonMain/kotlin/br/com/radioplayerbr/data/remote/RadioApiService.kt`:

```kotlin
private fun getMockRadioStations(): List<RadioStation> {
    return listOf(
        RadioStation(
            id = "seu-id",
            name = "Nome da Rádio",
            streamUrl = "https://url-do-stream",
            genre = "Pop",
            city = "São Paulo",
            state = "SP"
        ),
        // Adicione mais estações aqui
    )
}
```

### Integrar com API real

Substitua o método `getRadioStations()` em `RadioApiService.kt`:

```kotlin
suspend fun getRadioStations(): List<RadioStation> {
    return client.get("https://sua-api.com/radios").body()
}
```

### Personalizar cores

Edite `RadioPlayerApp.kt` para customizar o tema:

```kotlin
MaterialTheme(
    colorScheme = lightColorScheme(
        primary = Color(0xFF6200EE),
        // Adicione mais cores
    )
)
```

## Próximos Passos

Possíveis melhorias futuras:
- [ ] Integração com API real de rádios (ex: Radio Browser API)
- [ ] Notificação de mídia com controles
- [ ] Histórico de reprodução
- [ ] Sleep timer
- [ ] Equalizer
- [ ] Compartilhamento de estações
- [ ] Modo offline com cache
- [ ] Widget para Android
- [ ] Suporte a podcasts

## Licença

Este projeto é um exemplo educacional. Sinta-se livre para usar como base para seus próprios projetos.

## Contribuindo

Pull requests são bem-vindos! Para mudanças maiores, por favor abra uma issue primeiro.

## Suporte

Para questões e sugestões, abra uma issue no repositório.

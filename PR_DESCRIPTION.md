# 📻 Radio Player Brasil - KMP App Completo

Este PR adiciona um aplicativo completo de radio player multiplataforma para Android e iOS.

## 🎯 Principais Features

### 1. **API Real - Radio Browser**
- ✅ Integração com [Radio Browser API](https://radio-browser.info)
- ✅ Busca 50 rádios brasileiras mais populares
- ✅ Suporte a busca por nome com filtro de país
- ✅ Fallback para dados mock em caso de erro

### 2. **Arquitetura Moderna**
- ✅ **Kotlin 2.0.20** com compilador K2
- ✅ **Compose Multiplatform 1.7.0** - UI 100% compartilhada
- ✅ **androidx.lifecycle.ViewModel** - ViewModel apropriado com viewModelScope
- ✅ **Koin 3.5.6** - Injeção de dependência multiplataforma

### 3. **Funcionalidades**
- 📻 Player de áudio para Android (ExoPlayer) e iOS (AVPlayer)
- ⭐ Sistema de favoritos com persistência local
- 🔍 Busca e filtros de estações
- 🎨 Interface Material Design 3
- 🌙 Dark mode por padrão

## 📦 Estrutura

```
radioplayerbr/
├── shared/               # Código compartilhado KMP
│   ├── data/
│   │   ├── model/       # RadioStation, PlayerState
│   │   ├── remote/      # API Service + DTOs
│   │   └── repository/  # RadioRepository
│   ├── domain/          # AudioPlayer (expect/actual)
│   ├── presentation/    # ViewModel + UI Compose
│   └── di/              # Koin modules
├── androidApp/          # App Android
└── iosApp/             # App iOS (Compose puro)
```

## 🔧 Tecnologias

- **Kotlin** 2.0.20
- **Compose Multiplatform** 1.7.0
- **Ktor** 2.3.12 (networking)
- **Koin** 3.5.6 (DI)
- **Media3** 1.4.1 (Android player)
- **AVFoundation** (iOS player)

## 🚀 Como Testar

**Android:**
```bash
./gradlew :androidApp:installDebug
```

**iOS:**
```bash
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
# Abrir projeto no Xcode e rodar
```

## ✨ Melhorias Implementadas

1. **API Real**: Substituição de dados mock por API real do Radio Browser
2. **ViewModel Lifecycle**: Uso correto de androidx.lifecycle.ViewModel com viewModelScope
3. **Dependency Injection**: Koin configurado com módulos específicos de plataforma
4. **Clean Architecture**: Separação clara de camadas (data, domain, presentation)
5. **Error Handling**: Fallback gracioso para dados mock em caso de erro de rede

## 📝 Commits Principais

- ✅ Estrutura inicial do projeto KMP
- ✅ Kotlin 2.0.20 e Compose Multiplatform puro
- ✅ Gradle wrapper e preparação para CI
- ✅ Integração com API real + ViewModel + Koin DI

## 🎁 Próximos Passos (Futuro)

- [ ] Notificações de mídia
- [ ] Cache offline
- [ ] Sleep timer
- [ ] Equalizer
- [ ] Widget Android
- [ ] CI/CD completo

---

**Link do branch**: `claude/kmp-radio-player-app-8gURB`

**Para criar o PR**:
1. Acesse: https://github.com/jcaiqueoliveira/kmp-claude-code-subagents/pull/new/claude/kmp-radio-player-app-8gURB
2. Cole esta descrição
3. Clique em "Create pull request"

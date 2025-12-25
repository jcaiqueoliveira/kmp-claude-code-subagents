# Instruções para Adicionar o CI Kotlin

Devido às permissões do GitHub App, o workflow precisa ser adicionado manualmente.

## Opção 1: Adicionar via Interface Web do GitHub

1. Acesse o repositório no GitHub
2. Vá para a aba "Actions"
3. Clique em "set up a workflow yourself" ou "New workflow"
4. Copie e cole o conteúdo abaixo

## Opção 2: Adicionar via Git Local

1. Clone o repositório localmente (se ainda não tiver)
2. Crie o diretório: `mkdir -p .github/workflows`
3. Crie o arquivo `.github/workflows/kotlin-ci.yml` com o conteúdo abaixo
4. Commit e push

## Conteúdo do Workflow

Arquivo: `.github/workflows/kotlin-ci.yml`

```yaml
name: Kotlin CI

on:
  push:
    branches: [ main, claude/** ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@v3

    - name: Grant execute permission for gradlew
      run: chmod +x radioplayerbr/gradlew

    - name: Build with Gradle
      run: cd radioplayerbr && ./gradlew build --no-daemon

    - name: Run tests
      run: cd radioplayerbr && ./gradlew test --no-daemon
```

## O que este CI faz?

- ✅ Compila o projeto Kotlin Multiplatform
- ✅ Executa os testes
- ✅ Roda em pushes para `main` e branches `claude/**`
- ✅ Roda em pull requests para `main`
- ✅ Usa JDK 17 com Gradle caching para builds rápidas

Depois de adicionar o workflow, o CI rodará automaticamente em cada push/PR!

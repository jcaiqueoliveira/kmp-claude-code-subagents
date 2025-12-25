# 🚀 Como Ativar CI/CD no Repositório

## ⚠️ Por que não foi adicionado automaticamente?

O GitHub bloqueia a criação de workflows via GitHub App sem a permissão `workflows`.
A solução é adicionar manualmente via interface web.

## 📝 Passos para Adicionar (2 minutos)

### 1. Acesse o Repositório
https://github.com/jcaiqueoliveira/kmp-claude-code-subagents

### 2. Crie o Workflow
- Clique em **Actions** (topo da página)
- Clique em **New workflow**
- Clique em **set up a workflow yourself**

### 3. Configure o Arquivo
- Nome do arquivo: `ci.yml`
- Cole o conteúdo que está em `.github/workflows/ci.yml` (veja abaixo)
- Clique em **Commit changes...**
- Escolha "Commit directly to the `main` branch" ou crie um PR

### 4. Pronto! 🎉
A CI começará a rodar automaticamente.

## 📄 Conteúdo do Workflow

O arquivo `.github/workflows/ci.yml` está disponível localmente no repositório.
Para ver o conteúdo completo:

```bash
cat .github/workflows/ci.yml
```

Ou copie diretamente desta localização:
`/home/user/kmp-claude-code-subagents/.github/workflows/ci.yml`

## ✨ O que a CI faz?

Quando ativada, a CI irá automaticamente:

- 🤖 **Build Android**: Compila APK e roda testes
- 🍎 **Build iOS**: Compila framework shared para iOS
- ✨ **Lint**: Verifica qualidade de código
- 📦 **Artefatos**: Salva APK e framework para download

## 🎯 Quando roda?

- Em push para `main`, `master` ou `develop`
- Em Pull Requests para essas branches
- Apenas quando há mudanças em `radioplayerbr/**`

## 🔍 Ver Resultados

Após adicionar:
1. Vá em **Actions** no GitHub
2. Veja os workflows rodando
3. O badge no README ficará verde! ✅

## 📦 Arquivos Preparados

Já criados e prontos para uso:
- ✅ `.github/workflows/ci.yml` - Pipeline de CI
- ✅ `radioplayerbr/gradlew` - Gradle wrapper
- ✅ `radioplayerbr/.gitattributes` - Configurações Git

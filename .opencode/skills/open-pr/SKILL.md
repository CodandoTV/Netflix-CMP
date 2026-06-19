---
name: open-pr
description: Open a Pull Request comparing current feature branch against master, generating a pt-br description
---

# open-pr

Open a Pull Request on GitHub comparing the current branch against `master`, generating a description in **pt-br**.

## Behavior

1. **Detect current branch** — run `git rev-parse --abbrev-ref HEAD` to get the feature branch name.

2. **Fetch latest `master`** — run `git fetch origin master`.

3. **Analyze changes** — compare HEAD against `master`:

   ```bash
   git log master..HEAD --oneline --no-decorate
   git diff master...HEAD --stat
   ```

4. **Check for PR template** — look for a local PR template:

   ```bash
   TEMPLATE_FILE=".github/PULL_REQUEST_TEMPLATE.md"
   if [ -f "$TEMPLATE_FILE" ]; then
     TEMPLATE_CONTENT=$(cat "$TEMPLATE_FILE")
   else
     TEMPLATE_CONTENT=""
   fi
   ```

5. **Categorize commits** by conventional commit type:

   | Type | Label |
   |---|---|
   | `feat` | Nova funcionalidade |
   | `fix` | Correção |
   | `refactor` | Refatoração |
   | `docs` | Documentação |
   | `test` | Testes |
   | `chore` | Manutenção |
   | `build` | Build / CI |
   | `BREAKING` | Mudança crítica |

6. **Identify affected modules** — from changed file paths, determine which modules were modified:
   - `composeApp`
   - `feature-*`
   - `core-*`
   - `build-logic`
   - `androidApp`
   - `gradle` / config

7. **Generate PR body in pt-br** using the template if available:

   - **If `TEMPLATE_CONTENT` is not empty:** use the template file as the base structure. Ensure all auto-generated content (commit summaries, module lists, etc.) is written in **pt-br**. If the template contains English section headers (e.g. `## What was done?`, `## How to test?`), translate them to pt-br equivalents (e.g. `## O que foi feito?`, `## Como testar?`). Inject the analyzed commits, affected modules, and test steps into the appropriate sections of the template.

   - **If no template exists:** use the following hardcoded pt-br structure:

     ```markdown
     ## O que foi feito?

     [Resumo das alterações baseado nos commits]

     ## Módulos afetados

     - [lista de módulos]

     ## Tipo de mudança

     - [ ] Nova funcionalidade
     - [ ] Correção de bug
     - [ ] Refatoração
     - [ ] Documentação
     - [ ] Testes
     - [ ] Build / CI

     ## Como testar?

     1. [passos para testar]
     2. [passos adicionais]

     ## Checklist

     - [ ] Build passa localmente
     - [ ] Lint / Detekt não aponta erros novos
     - [ ] Testes existentes continuam passando
     - [ ] Testes novos foram adicionados (se aplicável)
     ```

8. **Determine PR title** — use the first commit subject line or a concise summary derived from the branch name (e.g. `feat: adiciona tela de perfil`).

9. **Open the PR** using `gh`:

   ```bash
   gh pr create \
     --base master \
     --head <current-branch> \
     --title "<title>" \
     --body "<body>"
   ```

10. If `gh` is not authenticated or available, print the full PR content to stdout with instructions.

## Notes

- All PR text MUST be in **pt-br** (Portuguese — Brazil), regardless of whether a template is used.
- Follow the project's existing commit style (conventional commits).
- If the branch has only one commit, use its message as the PR title.
- If the PR would be empty (no diff), warn the user.
- The skill looks for `.github/PULL_REQUEST_TEMPLATE.md` in the repo root. If found, its section structure is preserved but all injected content is in pt-br. If not found, a hardcoded pt-br template is used.

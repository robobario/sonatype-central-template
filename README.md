# template-library

Template for a single-library Maven project, published to Maven Central via
Sonatype's Central Portal, built on public GitHub with GitHub Actions.

## What this gives you

- `pom.xml` configured for `central-publishing-maven-plugin`: sources, javadoc
  and GPG signing on release, auto-published once validated.
- `.github/workflows/ci.yaml` — build and test on every push to `main` and
  every pull request.
- `.github/workflows/publish-snapshot.yaml` — deploys the current `-SNAPSHOT`
  to Central's snapshot repository on every push to `main`.
- `.github/workflows/release.yaml` — manual, single-step release: builds,
  signs, publishes to Central, tags, creates a GitHub release, bumps to the
  next snapshot.
- `.github/dependabot.yml` — weekly updates for Maven dependencies and GitHub
  Actions.

## Using this template

1. Rename the project: in `pom.xml` replace `groupId`, `artifactId`, `name`,
   `description`, `url`, `scm`, and the `developers` entry. Move
   `src/main/java/io/github/robobario/template` and
   `src/test/java/io/github/robobario/template` to your real package, update
   package declarations, delete `Greeter`/`GreeterTest`.
2. Verify your namespace on the
   [Central Portal](https://central.sonatype.com/publishing/namespaces). The
   `io.github.<username>` namespace verifies automatically against your
   GitHub account; anything else needs DNS verification.
3. Add the repository secrets listed below.
4. Push to `main`. CI runs; if the namespace has snapshot publishing enabled,
   a `-SNAPSHOT` deploys automatically.
5. Run the `Release` workflow from the Actions tab when ready to cut a
   release.

## Repository secrets

| Secret | Purpose |
|---|---|
| `CENTRAL_TOKEN_USERNAME` | Sonatype Central user token username |
| `CENTRAL_TOKEN_PASSWORD` | Sonatype Central user token password |
| `GPG_PRIVATE_KEY` | Armoured GPG private key used to sign release artifacts |
| `GPG_PASSPHRASE` | Passphrase for `GPG_PRIVATE_KEY` |

Generate the user token from your
[Central Portal account](https://central.sonatype.com/account). Generate a
GPG key with `gpg --full-generate-key`, export the private key with
`gpg --armor --export-secret-keys <key-id>`, and publish the public key to a
keyserver (e.g. `gpg --keyserver keyserver.ubuntu.com --send-keys <key-id>`) —
Central checks that the signing key is publicly resolvable.

If `main` is a protected branch, allow the release workflow to push to it
(e.g. include `github-actions[bot]` in the branches-that-can-push list), or
swap `github.token` in `release.yaml` for a PAT with repo-write access.

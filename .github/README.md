# CI/CD setup

## One-time setup

1. Verify your namespace on the
   [Central Portal](https://central.sonatype.com/publishing/namespaces). The
   `io.github.<username>` namespace verifies automatically against your
   GitHub account; anything else needs DNS verification.
2. Generate a Sonatype Central user token from your
   [Central Portal account](https://central.sonatype.com/account).
3. Generate a GPG key:
   ```
   gpg --full-generate-key
   gpg --armor --export-secret-keys <key-id>
   gpg --keyserver keyserver.ubuntu.com --send-keys <key-id>
   ```
   Central checks that the signing key is publicly resolvable, so the
   `--send-keys` step is required.
4. Add the repository secrets below (Settings → Secrets and variables →
   Actions).

| Secret | Purpose |
|---|---|
| `CENTRAL_TOKEN_USERNAME` | Sonatype Central user token username |
| `CENTRAL_TOKEN_PASSWORD` | Sonatype Central user token password |
| `GPG_PRIVATE_KEY` | Armoured GPG private key used to sign release artifacts |
| `GPG_PASSPHRASE` | Passphrase for `GPG_PRIVATE_KEY` |

5. If `main` is a protected branch, allow the release workflow to push to it
   (e.g. include `github-actions[bot]` in the branches-that-can-push list), or
   swap `github.token` in `release.yaml` for a PAT with repo-write access.

## Workflows

- **ci.yaml** — build and test on every push to `main` and every pull
  request.
- **publish-snapshot.yaml** — on push to `main`, deploys the current
  `-SNAPSHOT` to Central's snapshot repository. No-op once the version isn't
  a snapshot. Requires snapshot publishing enabled for the namespace.
- **release.yaml** — manual (Actions tab → Release → Run workflow). Takes
  `release-version` and `next-version` inputs, then: sets the release
  version, builds/signs/publishes it to Central, tags it, creates a GitHub
  release, sets the next `-SNAPSHOT` version, and pushes everything.

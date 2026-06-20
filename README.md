# WorldGuardExtended
Adds more modern and specific flags to WorldGuard.

Only compatible with Paper servers from **26.1**.

---

## New flags

### `disable-doors`
- If `true`, doors can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-trapdoors`
- If `true`, trapdoors can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-fence-gates`
- If `true`, fence gates can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

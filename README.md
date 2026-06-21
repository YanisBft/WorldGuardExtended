# WorldGuardExtended
Adds more modern and specific flags to WorldGuard.

Only compatible with Paper servers from **26.1**.

---

## New flags

### `disable-daylight-detectors`
- If `true`, daylight detectors can't be toggled in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-decorated-pots`
- If `true`, items can't be put in decorated pots in the region,
  even if WorldGuard's `use` and `chest-access` flag values are `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-doors`
- If `true`, doors can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-fence-gates`
- If `true`, fence gates can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

### `disable-trapdoors`
- If `true`, trapdoors can't be opened or closed in the region,
  even if WorldGuard's `use` flag value is `ALLOW`.
- If `false`, this flag has no effect and behavior is handled by broader flags.

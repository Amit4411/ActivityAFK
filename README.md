# ActivityAFK

> Player profiles, active playtime, AFK protection, AFK zones and restart-safe weekly rewards for Paper 26.2.

## Quick Start

1. Build with Java 25 and Gradle (`./gradlew build`).
2. Copy `build/libs/ActivityAFK-1.0.0.jar` to `/plugins`.
3. Install Vault and a Vault-compatible economy if balances/reward economy commands are used.
4. Install LuckPerms (optional) for rank/prefix display.
5. Start the server, then edit `plugins/ActivityAFK/config.yml`, `afk.yml`, `rewards.yml`, and `zones.yml`.
6. Set an IANA timezone and the weekly schedule. Reload with `/activity reload`.

SQLite is created at `plugins/ActivityAFK/activity.db`. Active time, AFK time, online time, reward state, winners and zones are persisted. AFK time is never included in active rankings.

## Commands

| Command | Description | Permission |
|---|---|---|
| `/info [player]` | Basic profile and active statistics | `activity.info`, others: `activity.info.others` |
| `/detailinfo [player]` | Full profile including AFK statistics | `activity.detailinfo`, others: `activity.detailinfo.others` |
| `/guiinfo [player]` | Read-only live chest GUI | `activity.guiinfo`, others: `activity.guiinfo.others` |
| `/afk` | Toggle manual AFK mode | `activity.afk` |
| `/activity top` | Current active-time leaderboard | `activity.top` |
| `/activity winners` | Previous weekly winners | `activity.winners` |
| `/activity reload` | Reload configuration | `activity.reload` |
| `/activity reset [player]` | Reset a player's statistics | `activity.reset` |
| `/activity resetweek` | Clear current weekly statistics | `activity.resetweek` |
| `/activity forcewin <player>` | Process the configured top reward for a player | `activity.forcewin` |
| `/afkzone create <name>` | Create a zone | `activity.zone.create` |
| `/afkzone delete <name>` | Delete a zone | `activity.zone.delete` |
| `/afkzone list` | List zones | `activity.zone` |
| `/afkzone info <name>` | Inspect a zone | `activity.zone` |
| `/afkzone setpos1/setpos2 <name>` | Set a corner at your location | `activity.zone.edit` |
| `/afkzone setreward/setprotection <name> <true\|false>` | Toggle zone behavior | `activity.zone.edit` |

All commands include context-aware tab completion. Staff can use `activity.afk.protection.bypass` and `activity.afk.bypass`.

## AFK and Activity

`/afk`, automatic inactivity, and configured zones mark a player AFK. Movement, camera rotation and configured interactions cancel AFK. Online time, active time and AFK time are separate counters. Automatic AFK defaults to five minutes. AFK protection covers configurable damage and nearby block griefing. Zone rewards may be disabled independently.

AFK rewards are console commands, limited by interval, maximum rewardable time, and daily count. The player can remain AFK after the maximum; only rewards stop. Keep AFK rewards below normal active gameplay earnings; the plugin cannot determine economic balance automatically.

## Weekly Competition

The leaderboard uses active seconds only. Weekly reward positions are arbitrary YAML keys, so positions 1/7/19 or 1..50 are both valid. Exact ties use competition ranking (`1, 1, 3`) and reward all tied players when enabled. Rewards are persisted with a unique week/player/position identity; offline recipients receive pending rewards on join and failed commands remain recoverable.

Configure `timezone`, `schedule.week-reset`, and `schedule.reward-time` with an IANA timezone such as `Asia/Kolkata`, `Europe/London`, or `UTC`. Daily/monthly displays and schedule calculations use it.

## Configuration Files

- `config.yml`: timezone, schedules, GUI and general behavior.
- `messages.yml`: user-facing messages.
- `afk.yml`: automatic AFK, protection and reward limits.
- `rewards.yml`: arbitrary weekly reward positions and console commands.
- `zones.yml`: cuboid zones.

Placeholders for reward commands include `[player]`, `[uuid]`, `[rank]`, `[balance]`, `[playtime]`, `[playtime_today]`, `[playtime_week]`, `[playtime_month]`, `[playtime_total]`, `[afk_today]`, `[afk_week]`, `[afk_month]`, `[afk_total]`, `[position]`, and `[week]`.

## Economy and Rank

Vault is optional. If no economy provider exists, balances display `Unavailable` and the plugin continues safely. LuckPerms is preferred for rank/prefix; a Vault permission provider is used as fallback. ActivityAFK never replaces a player's permanent rank.

## GUI

The GUI has exactly two configured information items by default, updates online cached data at the configured interval, and cancels all inventory/drag/drop/hotbar interactions. Closing, disconnecting or disabling cleans up refresh tasks.

## Troubleshooting

**Balance unavailable:** install Vault and a Vault-compatible economy. **Rank missing:** install/configure LuckPerms or Vault chat. **Reward missing:** inspect console errors, `rewards.yml`, pending reward records and command validity. **AFK reward missing:** check global/zone enablement, interval, daily limit and maximum reward time. **Wrong reset time:** verify the IANA timezone and schedule values.

## Developer

Author: **Corrupted Studio**  
Website: https://github.com/CorruptedStudio/

The code is split into persistence, activity/AFK services, command/tab-completion, GUI and integration helpers. SQLite uses UUID identity and UTC epoch timestamps so a future MySQL repository can replace the storage implementation without changing gameplay services.

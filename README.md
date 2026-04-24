# 114-2 FCU Framework Design - Homework 2

以 DIP（Dependency Inversion Principle）實作「通用遙控器」：可控制 `TV` 或 `AirConditioner` 做 `ON / OFF / UP / DOWN`，並使用 Swing 顯示面板與狀態。

## 功能規格
- **TV**
  - 預設頻道：7
  - 範圍：1 ~ 15（到邊界就維持在邊界）
- **AirConditioner**
  - 預設溫度：25°C
  - 範圍：20 ~ 30°C（到邊界就維持在邊界）
- **遙控器（RemoteController）**
  - 只依賴介面 `ControllableDevice`，不依賴 `Tv` / `AirConditioner` 具體類別

## 專案結構
- `src/fcu/remote/ControllableDevice.java`：裝置抽象介面
- `src/fcu/remote/Tv.java`：TV 實作
- `src/fcu/remote/AirConditioner.java`：冷氣實作
- `src/fcu/remote/RemoteController.java`：遙控器（高層模組）
- `src/fcu/remote/ui/RemoteControllerFrame.java`：Swing UI
- `src/fcu/remote/App.java`：程式入口（main）

## 執行方式（Windows / PowerShell）
確認你已安裝 JDK（建議 8 以上）。

在專案根目錄執行：

```powershell
mkdir out -ErrorAction SilentlyContinue
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
java -cp out fcu.remote.App
```

## 操作方式
- 由上方下拉選單切換裝置（TV / AirConditioner）
- 按 `ON / OFF / UP / DOWN` 操作
- 中央狀態列會即時顯示目前裝置狀態

## 交付文件（Markdown 轉 PDF）
作業報告 Markdown：`docs/Report.md`

- **截圖放置**：`docs/screenshots/`
- **轉 PDF（Pandoc）**：

```powershell
pandoc .\docs\Report.md -o .\docs\Report.pdf
```
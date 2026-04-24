# Framework Design Homework 2

## Intro:
    這是一個軟體框架的作業,需要使用 JAVA 開發一個簡單的程式。題目要求如下:
    請參考 DIP 原則 (Dependency Inversion Principle) 設計一個通用型的遙控器 RemoteController，可以對電視 (TV) 或冷氣 (AirConditioner) 做開、關、上、下 (on, off, up, down) 等動作。TV 預設的頻道是第七台，上下會在 1-15 間變化。冷氣預設 25 度，會在 20-30 度間變化。使用 Swing 來呈現此遙控器面板。

## 程式碼風格規範:
    - 使用物件導向化: 切勿將程式碼都實作在 main 函數中，應適當使用封裝、介面與相關供能。
    - 變數命名: 變數名稱應當與其功能相同，使用小駝峰進行命名。
    - class 命名: 使用大駝峰命名法命名。
    - 簡單化: 這只是一個小作業不是商品，只要求功能不要求完整功能，達成題目要求即可。
    - 註釋: 應在合理處加上註解，不應過多也不應過少。
    - 文件化: 建立 `README.md` 文件，完整說明其運作

## 設計目標（DIP）
    - 遙控器（高層模組）**只依賴抽象**（介面），不依賴 TV/冷氣（低層模組）的具體實作。
    - TV/冷氣以「實作介面」方式被注入（constructor injection 或 setter injection），RemoteController 不需要知道目前控制的是什麼裝置。

## 建議架構（最小可交付版本）
    - `ControllableDevice`（或 `Device`）介面
        - `void on()`
        - `void off()`
        - `void up()`
        - `void down()`
        - `String getStatusText()`（讓 UI 顯示狀態，例如「TV: ON, CH=7」/「AC: OFF, TEMP=25」）
    - `Tv` 類別（實作 `ControllableDevice`）
        - 預設頻道：7
        - 頻道範圍：1 ~ 15（`up/down` 到邊界就維持在邊界，不要循環；若你想做循環也可，但 README 要寫清楚）
    - `AirConditioner` 類別（實作 `ControllableDevice`）
        - 預設溫度：25
        - 溫度範圍：20 ~ 30（同上：邊界維持不變）
    - `RemoteController` 類別（高層模組）
        - 欄位：`private ControllableDevice device;`
        - 提供操作：`pressOn/pressOff/pressUp/pressDown`
        - 可切換裝置：`setDevice(ControllableDevice device)`

## Swing UI 需求（最小面板）
    - 視窗標題：自行命名（例：Remote Controller）
    - 元件建議：
        - 一個狀態顯示區（`JLabel` 或 `JTextArea`）：顯示目前裝置名稱、電源狀態、頻道/溫度
        - 四個按鈕：`ON / OFF / UP / DOWN`
        - 一個裝置切換（擇一即可）：
            - `JComboBox`（TV / AirConditioner）
            - 或兩個 `JRadioButton`（搭配 `ButtonGroup`）
    - 行為：
        - 切換裝置後，遙控器後續按鍵直接作用於新裝置（透過 `RemoteController.setDevice(...)`）
        - 每次按鍵操作後，UI 立刻刷新狀態文字（`getStatusText()`）

## 交付文件
    - PDF文件: 使用 MarkDown 先撰寫好文件後，轉換為 PDF
    - 文件內容:
        - 姓名: 王建葦
        - 學號: D1210799
        - 班級: 資訊三乙
        - 題目: 通用型遙控器
        - 設計方法概述
        - 程式、執行畫面及其說明
        - 參考資料
        - AI 使用狀況與心得
        - 概述提問的內容，以及 AI 的回答
        - 你手動(沒有用AI)的部份
        - 心得（AI的實用性、限制、對你學習的影響）

## 驗收清單（你交作業前自測）
    - 遙控器控制 TV：預設 CH=7，UP/DOWN 只在 1~15 內變化
    - 遙控器控制冷氣：預設 TEMP=25，UP/DOWN 只在 20~30 內變化
    - RemoteController 沒有依賴 `Tv` / `AirConditioner` 具體型別（只依賴介面）
    - Swing 面板可操作，並能切換控制對象
    - `README.md` 有：如何執行、操作方式、你採用的邊界規則（是否循環）與主要類別說明
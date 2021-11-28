export interface GeanAppodealPlugin {
  initializeAppodeal(options: { key: string, useTestAds: boolean }): Promise<boolean>;
  showInterstitial(): Promise<boolean>;
  

  // setTesting(options: { value: boolean }): Promise<{ value: boolean }>;
  // setKey(options: { key: string }): Promise<{ key: string }>;
}

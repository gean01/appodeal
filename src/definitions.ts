export interface GeanAppodealPlugin {
  initializeAppodeal(options: { useTestAds: boolean }): Promise<boolean>;
  showInterstitial(): Promise<boolean>;
  

  // setTesting(options: { value: boolean }): Promise<{ value: boolean }>;
  setKey(options: { key: string }): Promise<{ key: string }>;
}

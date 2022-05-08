export interface GeanAppodealPlugin {
  initializeAppodeal(options: { key: string, isDev: boolean, useTestAds: boolean, consent: boolean }): Promise<boolean>;
  showInterstitial(): Promise<boolean>;
  showBanner(): Promise<boolean>;
  interstitialIsLoaded(): Promise<boolean>;
}



```
;
;   Label           OPC     Operand/Arg(s)
;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;   ;
;
											
               .include     "STDLIB.a65", list=.list
											
               .include     "STDLIB.def.a65", as=.STDL, list=.list

                   .STDL:.DECLARE   .STDL:V1, list=.list


               .org      $0200

                   .STDL:.JSRWPB    mySub, @eopb, list=.list
        argA       .byte    $00
        argB       .word    $FFFF
        @eopb

                    NOP                 ; Execution returns here


               .align    0x0F

    mySub
                    NOP                 ; Body of subroutine

                   .STDL:.RTSWPB    list=.list

                    NOP                 ; Body of subroutine

                   .STDL:.RTSWPB    list=.list


                    NOP
```


```
LOC   CODE         LABEL         INSTRUCTION

0200                             * = $0200                      ┌──────────────────────────────────────────────────────────┐
0200  20 10 02                   JSR $0210                      │                                                          │
0203  07 02                      .WORD $0207                    │   LOC  CODE      INSTRUCTION   |AC XR YR SP|-NV-DIZC|#   │
0205  00                         .BYTE $00                      │                                                          │
0206  FF 0F                      .WORD $0FFF                    │   0200 20 10 02  JSR $0210     |00 00 00 FD|-00-0110|6   │
0208               EOPB                                         │   0210 EA        NOP           |00 00 00 FD|-00-0110|2   │
0208  EA                         NOP                            │   0211 68        PLA           |02 00 00 FE|-00-0100|4   │
0209  00                         BRK                            │   0212 A8        TAY           |02 00 02 FE|-00-0100|2   │
0210                             * = $0210                      │   0213 68        PLA           |02 00 02 FF|-00-0100|4   │
0210               MYSUB                                        │   0214 AA        TAX           |02 02 02 FF|-00-0100|2   │
0210  EA                         NOP                            │   0215 C8        INY           |02 02 03 FF|-00-0100|2   │
0211  68                         PLA                            │   0216 84 00     STY $00       |02 02 03 FF|-00-0100|3   │
0212  A8                         TAY                            │   0218 D0 01     BNE $021B     |02 02 03 FF|-00-0100|3   │
0213  68                         PLA                            │   021B 86 01     STX $01       |02 02 03 FF|-00-0100|3   │
0214  AA                         TAX                            │   021D A0 01     LDY #$01      |02 02 01 FF|-00-0100|2   │
0215  C8                         INY                            │   021F B1 00     LDA ($00),Y   |02 02 01 FF|-00-0100|5   │
0216  84 00                      STY $00                        │   0221 48        PHA           |02 02 01 FE|-00-0100|3   │
0218  D0 01                      BNE $021B                      │   0222 88        DEY           |02 02 00 FE|-00-0110|2   │
021A  E8                         INX                            │   0223 B1 00     LDA ($00),Y   |07 02 00 FE|-00-0100|5   │
021B               NE                                           │   0225 48        PHA           |07 02 00 FD|-00-0100|3   │
021B  86 01                      STX $01                        │   0226 60        RTS           |07 02 00 FF|-00-0100|6   │
021D  A0 01                      LDY #$01                       │   0208 EA        NOP           |07 02 00 FF|-00-0100|2   │
021F  B1 00                      LDA ($00),Y                    │   0209 00        BRK           |07 02 00 FC|-00-0100|7   │
0221  48                         PHA                            │   * BRK at 0209 => 0000                                  │
0222  88                         DEY                            │                                                          │
0223  B1 00                      LDA ($00),Y                    └──────────────────────────────────────────────────────────┘
0225  48                         PHA
0226  60                         RTS
0000                             * = $0000
0000  FF FF        RAP           .WORD $FFFF

```
